package org.purl.wf4ever.astrotaverna.tjoin;

import java.util.ArrayList;
import java.util.List;

import net.sf.taverna.t2.visit.VisitReport;
import net.sf.taverna.t2.visit.VisitReport.Status;
import net.sf.taverna.t2.workflowmodel.health.HealthCheck;
import net.sf.taverna.t2.workflowmodel.health.HealthChecker;


/**
 * Stilts health checker
 * @author Julian Garrido
 * @since    19 May 2011
 */
public class CrossMatchListActivityHealthChecker implements
		HealthChecker<CrossMatchListActivity> {

	public boolean canVisit(Object o) {
		// Return True if we can visit the object. We could do
		// deeper (but not time consuming) checks here, for instance
		// if the health checker only deals with StiltsActivity where
		// a certain configuration option is enabled.
		return o instanceof CrossMatchListActivity;
	}

	public boolean isTimeConsuming() {
		// Return true if the health checker does a network lookup
		// or similar time consuming checks, in which case
		// it would only be performed when using File->Validate workflow
		// or File->Run.
		return false;
	}

	public VisitReport visit(CrossMatchListActivity activity, List<Object> ancestry) {
		CrossMatchListActivityConfigurationBean config = activity.getConfiguration();

		// We'll build a list of subreports
		List<VisitReport> subReports = new ArrayList<VisitReport>();

		/*
		if (!config.getExampleUri().isAbsolute()) {
			// Report Severe problems we know won't work
			VisitReport report = new VisitReport(HealthCheck.getInstance(),
					activity, "Example URI must be absolute", HealthCheck.INVALID_URL,
					Status.SEVERE);
			subReports.add(report);
		}

		if (config.getExampleString().equals("")) {
			// Warning on possible problems
			subReports.add(new VisitReport(HealthCheck.getInstance(), activity,
					"Example string empty", HealthCheck.NO_CONFIGURATION,
					Status.WARNING));
		}

		*/
		
		if(!(config.getTypeOfInput().compareTo("File")==0
				|| config.getTypeOfInput().compareTo("URL")==0
				|| config.getTypeOfInput().compareTo("String")==0)){
			subReports.add(new VisitReport(HealthCheck.getInstance(), activity,
					"Invalid input type.", HealthCheck.INVALID_CONFIGURATION,
					Status.WARNING));
		}
		
		if(!(config.getFixcols().compareTo("none")==0
				|| config.getFixcols().compareTo("dups")==0
				|| config.getFixcols().compareTo("all")==0)){
			subReports.add(new VisitReport(HealthCheck.getInstance(), activity,
					"Invalid fixcols parameter.", HealthCheck.INVALID_CONFIGURATION,
					Status.WARNING));
		}
		
		if(!(config.getJoin().compareTo("default")==0
				|| config.getJoin().compareTo("match")==0
				|| config.getJoin().compareTo("nomatch")==0
				|| config.getJoin().compareTo("always")==0)){
			subReports.add(new VisitReport(HealthCheck.getInstance(), activity,
					"Invalid join parameter.", HealthCheck.INVALID_CONFIGURATION,
					Status.WARNING));
		}
		
		if(!(config.getMatchCriteria().compareTo("sky")==0
				|| config.getMatchCriteria().compareTo("skyerr")==0
				|| config.getMatchCriteria().compareTo("skyellipse")==0
				|| config.getMatchCriteria().compareTo("sky3d")==0
				|| config.getMatchCriteria().compareTo("exact")==0
				|| config.getMatchCriteria().compareTo("1d")==0
				|| config.getMatchCriteria().compareTo("2d")==0
				|| config.getMatchCriteria().compareTo("2d_anisotropic")==0
				|| config.getMatchCriteria().compareTo("1d_err")==0
				|| config.getMatchCriteria().compareTo("2d_err")==0
				|| config.getMatchCriteria().compareTo("2d_ellipse")==0
				|| config.getMatchCriteria().compareTo("other")==0)){
			subReports.add(new VisitReport(HealthCheck.getInstance(), activity,
					"Invalid matcher parameter.", HealthCheck.INVALID_CONFIGURATION,
					Status.WARNING));
		}
		
		if(!(config.getMultimode().compareTo("pairs")==0
				|| config.getMultimode().compareTo("group")==0)){
			subReports.add(new VisitReport(HealthCheck.getInstance(), activity,
					"Invalid multimode parameter.", HealthCheck.INVALID_CONFIGURATION,
					Status.WARNING));
		}

		// The default explanation here will be used if the subreports list is
		// empty
		return new VisitReport(HealthCheck.getInstance(), activity,
				"Stilts service OK", HealthCheck.NO_PROBLEM, subReports);
	}

}
