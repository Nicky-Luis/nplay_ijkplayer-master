package com.nickyluis.nplay_ijkplayer.common.manager;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {
	private static Stack<Activity> activityStack;
	private static ActivityManager instance;

	private ActivityManager() {
	}

	public static Stack<Activity> getActivityStack() {
		return activityStack;
	}

	public static void setActivityStack(Stack<Activity> activityStack) {
		ActivityManager.activityStack = activityStack;
	}

	public static ActivityManager getInstence() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		
		return instance;
	}

	//出栈
	public void popActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	//当前activity
	public Activity currentActivity() {
		Activity activity = null;
		if (!activityStack.empty())
			activity = activityStack.lastElement();
		return activity;
	}

	//进栈
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	//全出
	@SuppressWarnings("rawtypes")
	public void popAllActivityExceptOne(Class cls) {
//		while (true) {
			
//		for (Activity activity: activityStack) {
//			if (activity.getClass().equals(cls)) {
//				continue;
//			}	
//			popActivity(activity);
//		}
			
//			Activity activity = currentActivity();
//			if (activity == null) {
//				break;
//			}
//			if (activity.getClass().equals(cls)) {
//				break;
//			}
			
//		}
	}

	/**
	 * 退出app
	 */
	public void exitApplication() {
		System.out.println("退出app");
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			popActivity(activity);
		}
	}
}
