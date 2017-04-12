package radar.information.analysis;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.math3.distribution.NormalDistribution;

import radar.exception.ParameterNotSctochasticException;
import radar.exception.StatsException;

/**
 * @author David Stefan
 */
public class InformationAnalysis {

	
	/**
	 * This method computes the expected value of total perfect information (evtpi). evtpi gives an upper bound to the information that would result from additional data collection and analysis
	 * @param nbs simulation matrix of the specified information value objective. 
	 * @return returns the computed evpi value.
	 */
    public static double evpi(double[][] nbs) {
        return VectorUtils.mean(VectorUtils.colMax(nbs)) - VectorUtils.max(VectorUtils.rowMeans(nbs));
    }
    /**
	 * This method computes the expected value of partial perfect information (evppi). evppi gives an upper bound to how much we should pay to reduce uncertainty about a model parameter.
	 * @param param model parameter for which we intend to reduce uncertainty.
	 * @param nbs simulation matrix of the specified information value objective. 
	 * @return returns the computed evppi value.
	 */
    public static double evppi(double[] param, double[][] nbs) {
    	if (param == null){
    		//During parsing, we populate the list of parameter varibles, but when a paramter (e.g. Cost_OrderChunking[Order Chunking]) is not part of the selected optimal solutions, 
    		//its simData will be null during the simulation done for computing evtp and evppi. Thus added this check
    		return 0;
    	}
    	double [][] nbs_copy = new double [nbs.length][nbs[0].length];
    	for (int i =0; i <nbs.length;i ++ ){
			for (int j = 0 ; j < nbs[0].length; j ++){
				nbs_copy[i][j] = nbs[i][j];
			}
		}
    	return evppi_changes_nbs(param, nbs_copy);
    }
    private static double evppi_changes_nbs(double[] param, double[][] nbs) {

        // Check if param has at least 100 samples
        if (param.length < 100) {
            throw new StatsException("Too few samples.");
        }

        // Ensure parameter is stochastic.
        double val = param[0];
        boolean isStochastic = false;

        for (int i = 1; i < param.length; i++) {
            if (val != param[i]) {
                isStochastic = true;
                break;
            }
        }
        
        /*
        if (!isStochastic) {
            throw new ParameterNotSctochasticException("Input parameter is not stochastic.");
        }*/

        int n = param.length;
        int d = nbs.length;

        // Ensure input parameter and net benefit matrix have the same number of samples.
        if (nbs[0].length != n) {
            throw new StatsException("Input parameter and simulation matrix must have the same dimension.");
        }

        // Sort param and nbs by param
        VectorUtils.quickSortPivot(param, nbs);

        // If nbs has one row only, add row of zeros
        if (d == 1) {
            double[] zeros = VectorUtils.zeros(n);
            nbs = MatrixUtils.addRow(nbs, zeros);
        }

        // Create segmentation templatexxxxxxx
        int[][] nSegs = new int[d][d];

        for (int[] row : nSegs) {
            Arrays.fill(row, 1);
        }

        int[] segPoints = new int[0];
        int segPoint;

        for (int i = 0; i < d - 1; i++) {

            for (int j = i + 1; j < d; j++) {

                double[] cm = VectorUtils.div(VectorUtils.cumsum(VectorUtils.diff(nbs[i], nbs[j])), n);

                if (nSegs[i][j] == 1) {

                    int l = VectorUtils.whichMin(cm);
                    int u = VectorUtils.whichMax(cm);

                    if (cm[u] - Math.max(cm[0], cm[n - 1]) > Math.min(cm[0], cm[n - 1]) - cm[l]) {
                        segPoint = u;
                    } else {
                        segPoint = l;
                    }

                    if (segPoint > 0 && segPoint < n - 1) {

                        int[] newSegPoints = new int[segPoints.length + 1];
                        int k = 0;

                        for (; k < segPoints.length; k++) {
                            newSegPoints[k] = segPoints[k];
                        }

                        newSegPoints[k] = segPoint;
                        segPoints = newSegPoints;
                    }
                }
            }
        }

        double evppi = 0;

        if (segPoints.length > 0) {

            VectorUtils.quickSort(segPoints);

            // Copy segmentation points into an array extended by 0 at the start
            // and n - 1 at the end
            int[] newSegPoints = new int[segPoints.length + 2];
            newSegPoints[0] = -1;
            for (int i = 1; i < newSegPoints.length - 1; i++) {
                newSegPoints[i] = segPoints[i - 1];
            }
            newSegPoints[newSegPoints.length - 1] = n - 1;

            // Choose unique segmentation points
            int[] segPoints2 = VectorUtils.unique(newSegPoints);

            for (int j = 0; j < segPoints2.length - 1; j++) {

                double max = Double.NEGATIVE_INFINITY;

                for (double[] nb : nbs) {
                    double sum = 0.0;
                    for (int l = segPoints2[j] + 1; l < segPoints2[j + 1] + 1; l++) {
                        sum += nb[l];
                    }
                    max = Math.max(max, sum);
                }

                evppi = evppi + max / n;
            }

            double[] means = VectorUtils.rowMeans(nbs);
            double max = VectorUtils.max(means);

            evppi = evppi - max;

        } else {
            evppi = 0;
        }

        return evppi;
    }

    /**
     * Computes the expected value of partial perfect information.
     * Implementation mirrors an efficient, one-level simulation method by
     * Mohsen Sadatsafavi.
     *
     * @see <a
     * href="http://webservices.heartandlung.ca/research/other/1evppi/">Efficient
     * algorithm for EVPPI calculation</a>
     *
     * @param param
     * @param NB
     * @return
     */
    public static double evppi2(double[] param, double[][] NB) {

        // Check if parameters have at least 100 samples.
        if (param.length < 100) {
            throw new StatsException("Too few samples.");
        }

        // Ensure parameter is stochastic.
        double val = param[0];
        boolean isStochastic = false;

        for (int i = 1; i < param.length; i++) {
            if (val != param[i]) {
                isStochastic = true;
                break;
            }
        }

       /* if (!isStochastic) {
            throw new ParameterNotSctochasticException("Input parameter is not stochastic.");
        }*/

        int N = param.length;
        int d = NB.length;

        // Ensure input parameter and net benefit matrix have the same number of samples.
        if (NB[0].length != N) {
            throw new StatsException("Input parameter and net benefit matrix must have the same dimension.");
        }

        // Clone input parameter to preserve the original array
        double[] x = param.clone();

        // Clone net benefit matrix, same reason as above
        double[][] y = new double[NB.length][];
        for (int i = 0; i < NB.length; i++) {
            y[i] = NB[i].clone();
        }

        // If NB is a vector, add row of zeros
        if (d == 1) {
            double[] zeros = VectorUtils.zeros(N);
            y = MatrixUtils.addRow(y, zeros);
        }

        // Sort parameter and NB
        VectorUtils.quickSortPivot(x, y);

        double[][] nbDiffs = new double[d * (d - 1) / 2][N];
        double[][] partialSums = new double[d * (d - 1) / 2][N];

        double minX = 0;
        double maxX = 0;
        int minWhere = 0;
        int maxWhere = 0;

        double x0, x1;
        double zeta, zetaMin, zetaMax;
        double p, minP = 1;
        double sigLevel = 0.1;

        int[] roots = new int[100];
        int rootsN = 0;

        int counter = 0;

        for (int i = 0; i < d; i++) {

            for (int j = i + 1; j < d; j++) {

                double v = 0;

                for (int k = 0; k < N; k++) {
                    nbDiffs[counter][k] = y[i][k] - y[j][k];
                    if (k == 0) {
                        partialSums[counter][k] = nbDiffs[counter][k];
                        minX = partialSums[counter][k];
                        minWhere = 0;
                        maxX = partialSums[counter][k];
                        maxWhere = 0;
                    } else {
                        partialSums[counter][k] = partialSums[counter][k - 1] + nbDiffs[counter][k];
                        if (partialSums[counter][k] < minX) {
                            minX = partialSums[counter][k];
                            minWhere = k;
                        }
                        if (partialSums[counter][k] > maxX) {
                            maxX = partialSums[counter][k];
                            maxWhere = k;
                        }
                    }
                    v += Math.pow(nbDiffs[counter][k], 2) / N;
                }

                x0 = partialSums[counter][0];
                x1 = partialSums[counter][N - 1];

                zetaMin = Math.min(x1 - minX, x0 - minX);
                zetaMax = Math.min(maxX - x0, maxX - x1);

                zeta = Math.max(zetaMin, zetaMax);
                p = 2 * new NormalDistribution(0, 1).cumulativeProbability(-zeta / Math.sqrt(v * N));

                if (p < minP) {
                    minP = p;
                }

                if (p < sigLevel) {
                    rootsN += 1;
                    if (zetaMin > zetaMax) {
                        roots[rootsN] = minWhere;
                    } else {
                        roots[rootsN] = maxWhere;
                    }
                }
                counter++;
            }
        }

        roots[0] = -1;
        rootsN = rootsN + 1;
        roots[rootsN] = N - 1;

        double runSum = 0;
        double temp;
        double localMax = 0;
        double eMaxNb = 0;

        for (int i = 0; i < rootsN; i++) {
            for (int j = 0; j < d; j++) {
                temp = 0;
                for (int k = roots[i] + 1; k < roots[i + 1] + 1; k++) {
                    temp += y[j][k];
                }
                if (j == 0) {
                    localMax = temp;
                } else if (temp > localMax) {
                    localMax = temp;
                }
            }
            runSum += localMax;
        }

        for (int i = 0; i < d; i++) {
            temp = 0;
            for (int k = 0; k < N; k++) {
                temp += y[i][k];
            }
            if (i == 0) {
                eMaxNb = temp;
            } else if (temp > eMaxNb) {
                eMaxNb = temp;
            }
        }

        double res = runSum - eMaxNb;
        return res / N;
    }
}
