package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.Comment;

public class CommentInstanceCreator implements
		InstanceCreator<ArrayList<Comment>> {

	@Override
	public ArrayList<Comment> createInstance(Type arg0) {
		ArrayList<Comment> list = new ArrayList<Comment>();
		return list;
	}

}
