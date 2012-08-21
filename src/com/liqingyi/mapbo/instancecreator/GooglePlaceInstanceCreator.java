package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.google.GooglePlace;

public class GooglePlaceInstanceCreator implements
		InstanceCreator<ArrayList<GooglePlace>> {

	@Override
	public ArrayList<GooglePlace> createInstance(Type arg0) {
		ArrayList<GooglePlace> list = new ArrayList<GooglePlace>();
		return list;
	}

}
