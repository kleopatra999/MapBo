package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.User;

public class UserInstanceCreator implements InstanceCreator<ArrayList<User>>{

	@Override
	public ArrayList<User> createInstance(Type arg0) {
		ArrayList<User> list=new ArrayList<User>();
		return list;
	}

}
