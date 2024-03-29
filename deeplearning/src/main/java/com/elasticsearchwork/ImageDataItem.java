package com.elasticsearchwork;

public class ImageDataItem {
	
	private String LineId;
    private String LineItem;

    public String getLineId() {
        return LineId;
    }

    public void setLineId(String LineId) {
        this.LineId = LineId;
    }

    public String getLineItem() {
        return LineItem;
    }

    public void setLineItem(String LineItem) {
        this.LineItem = LineItem;
    }

    @Override
    public String toString() {
        return String.format("Line Item Id{LineId='%s', LineItem='%s'}", LineId, LineItem);
    }

}
