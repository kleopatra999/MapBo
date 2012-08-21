package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.Geo;

public class GeoInstanceCreator implements InstanceCreator<ArrayList<Geo>> {

	@Override
	public ArrayList<Geo> createInstance(Type arg0) {
		ArrayList<Geo> list = new ArrayList<Geo>();
		return list;
	}

}
