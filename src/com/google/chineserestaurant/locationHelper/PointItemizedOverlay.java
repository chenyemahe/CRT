package com.google.chineserestaurant.locationHelper;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PointItemizedOverlay  extends ItemizedOverlay<OverlayItem> {

    private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();

    public PointItemizedOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
        // TODO Auto-generated constructor stub
    }
    
    public void addOverlay(OverlayItem overlay) {
        mOverlays.add(overlay);
        populate();
    }

    public void removeOverlay(int index) {
        if (mOverlays.size() > 0)
            mOverlays.remove(index);
    }

    @Override
    protected OverlayItem createItem(int i) {
        // TODO Auto-generated method stub
        return mOverlays.get(i);

    }

    @Override
    public int size() {
        // TODO Auto-generated method stub

        return mOverlays.size();
    }
}
