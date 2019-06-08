package com.gw.xact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(classes = { XactApplication.class })
@SpringBootTest(classes = XactApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XactApplicationTests {


	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private IdentityService identityService;

	// @Test
	public void TestStartProcess() {
		System.out.println("Start.........");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("leave", "1");
		System.out.println("流程启动成功，流程id:" + pi.getId());

	}

	// @Test
	public void testUser() {
		User user = identityService.newUser("ganwei_20190603");
		user.setFirstName("gan");
		user.setLastName("wei");
		user.setEmail("ganwei@abc.com");
		identityService.saveUser(user);
		User userInDb = identityService.createUserQuery().userId("ganwei").singleResult();
		assertNotNull(userInDb);
		identityService.deleteUser("ganwei");
		userInDb = identityService.createUserQuery().userId("ganwei").singleResult();
		assertNull(userInDb);

	}

	// @Test
	public void testGroup() {
		Group group = identityService.newGroup("deptLeader");
		group.setName("部门领导");
		group.setType("assignment");
		identityService.saveGroup(group);
		List<Group> groupList = identityService.createGroupQuery().groupId("deptLeader").list();
		assertEquals(1, groupList.size());
		identityService.deleteGroup("deptLeader");
		groupList = identityService.createGroupQuery().groupId("deptLeader").list();
		assertEquals(0, groupList.size());

	}

	// @Test
	public void testUserAndGroupMemership() {
		Group group = identityService.newGroup("deptLeader");
		group.setName("部门领导");
		group.setType("assignment");
		identityService.saveGroup(group);

		User user = identityService.newUser("henryyan");
		user.setFirstName("Henry");
		user.setLastName("Yan");
		user.setEmail("ganwei@qq.com");
		identityService.saveUser(user);

		identityService.createMembership("henryyan", "deptLeader");
		User userInGroup = identityService.createUserQuery().memberOfGroup("deptLeader").singleResult();
		assertNotNull(userInGroup);
		assertEquals("henryyan", userInGroup.getId());

		Group groupContainsHenryyan = identityService.createGroupQuery().groupMember("henryyan").singleResult();
		assertNotNull(groupContainsHenryyan);
		assertEquals("deptLeader", groupContainsHenryyan.getId());
	}

	@Before
	public void init() throws Exception {
		System.out.println("in init()");
	}

	@After
	public void post() throws Exception {
		// identityService
	}
	@Test
	public void UserAndGroupInUserTaskTest() {
		System.out.println("start... userandgroupinusertasktest");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("userAndGroupInUserTask");
		assertNotNull(pi);

		// 根据角色查询任务
		Task task = taskService.createTaskQuery().taskCandidateUser("henryyan").singleResult();
		assertNotNull(task);
		taskService.claim(task.getId(), "henryyan");
		taskService.complete(task.getId());
		System.out.println("finished");
	}

	@Test
	public void testGit() {
		System.out.println("hello git");
	}
	// @Test
	public void findTasksByUserId() {
		String userId = "xiaoming";
		List<Task> resultTask = taskService.createTaskQuery().processDefinitionKey("myProcess")
				.taskCandidateOrAssigned(userId).list();
		System.out.println("任务列表：" + resultTask);
		taskService.claim("22501", "xiaoming");
	}

}
