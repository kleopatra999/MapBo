package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.State;

public class StateInstanceCreator implements InstanceCreator<ArrayList<State>>{

	@Override
	public ArrayList<State> createInstance(Type arg0) {
		ArrayList<State> list=new ArrayList<State>();
		return list;
	}

}
