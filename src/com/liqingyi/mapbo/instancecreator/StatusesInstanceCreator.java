package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.Status;

public class StatusesInstanceCreator implements InstanceCreator<ArrayList<Status>>{

	@Override
	public ArrayList<Status> createInstance(Type arg0) {
		ArrayList<Status> status=new ArrayList<Status>();
		return status;
	}

}
