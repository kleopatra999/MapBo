package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.google.Review;

public class ReviewInstanceCreator implements
		InstanceCreator<ArrayList<Review>> {

	@Override
	public ArrayList<Review> createInstance(Type arg0) {
		ArrayList<Review> list = new ArrayList<Review>();
		return list;
	}

}
