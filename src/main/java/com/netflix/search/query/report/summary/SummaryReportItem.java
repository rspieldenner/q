package com.netflix.search.query.report.summary;

import java.util.Map;

import com.netflix.search.query.report.ReportItem;
import com.netflix.search.query.report.ResultType;

public class SummaryReportItem extends ReportItem {
    public SummaryReportItem(String name, Integer titles, Integer queries, Double precision, Double recall, Double fmeasure, Map<ResultType, Integer> counters) {
        super();

        setValue(SummaryReportHeader.name.toString(), name);
        setValue(SummaryReportHeader.titles.toString(), String.valueOf(titles));
        setValue(SummaryReportHeader.queries.toString(), String.valueOf(queries));
        for (ResultType type : ResultType.values()) {
            Integer counter = counters.get(type);
            if (counter == null)
                counter = 0;
            setValue(type.toString(), String.valueOf(counter));
        }
        setValue(SummaryReportHeader.precision.toString(), (String.format("%.2f", (precision * 100)) + SummaryReport.PERCENT_SIGN));
        setValue(SummaryReportHeader.recall.toString(), (String.format("%.2f", (recall * 100)) + SummaryReport.PERCENT_SIGN));
        setValue(SummaryReportHeader.fmeasure.toString(), (String.format("%.2f", (fmeasure * 100)) + SummaryReport.PERCENT_SIGN));
        setKey(getKeyFromNamedValues());
    }

    public SummaryReportItem(Map<String, String> namedValues) {
        super(namedValues);
    }

    public SummaryReportItem() {
        super();
    }

    @Override
    protected void appendKeyFromNamedValues(String headerValue, String value)
    {
        if (headerValue.equals(SummaryReportHeader.name.toString()))
            setKey(value);
    }

    @Override
    protected String getKeyFromNamedValues()
    {
        return getNamedValues().get(SummaryReportHeader.name.toString());
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SummaryReportItem other = (SummaryReportItem) obj;
        if (getKey() == null) {
            if (other.getKey() != null)
                return false;
        } else if (!getKey().equals(other.getKey()))
            return false;
        return true;
    }

}
