/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services;

import com.heinvdende.versionreview.test.client.domain.Task;
import com.heinvdende.versionreview.test.client.domain.User;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface TaskInfoService {
    public List<Task> getTasksByUser(User user);
    public Task getTaskById(long id);
}
