package com.mplatform.web;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mplatform.domain.TaskInfo;
import com.mplatform.service.TaskService;
import com.mplatform.util.DateUtil;

@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String task(@RequestBody String body) throws JSONException {
		JSONObject jo = new JSONObject(body);
		String taskName = jo.get("taskName").toString();
		Integer taskManager = Integer.parseInt(jo.get("taskManager").toString());
		String taskMember = jo.get("taskMember").toString();
		String taskStart = jo.get("taskStart").toString();
		String taskDead = jo.get("taskDead").toString();
		String taskInfo = jo.get("taskInfo").toString();
		String taskTips = jo.get("taskTips").toString();
		// time格式处理
		String startTemp = taskStart.substring(0, taskStart.length() - 5).replace("T", " ");
		String endTemp = taskDead.substring(0, taskDead.length() - 5).replace("T", " ");
		Timestamp startTime = DateUtil.str2timestamp(startTemp);
		Timestamp endTime = DateUtil.str2timestamp(endTemp);

		boolean result = taskService.insertTask(taskName, taskManager, taskMember, startTime, endTime, taskInfo, taskTips);
		if (!result)
			return "error";
		else
			return "true";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public List<TaskInfo> selectTask(HttpServletRequest request, HttpServletResponse response) {
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		Integer count = taskService.taskCount();
		response.setHeader("x-total-count", count.toString());
		return taskService.selectTask(page, limit);
	}
}