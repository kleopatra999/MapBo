package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.google.Prediction;


public class PredictionInstanceCreator implements InstanceCreator<ArrayList<Prediction>> {

	@Override
	public ArrayList<Prediction> createInstance(Type arg0) {
		ArrayList<Prediction> arrayList = new ArrayList<Prediction>();
		return arrayList;
	}

}
