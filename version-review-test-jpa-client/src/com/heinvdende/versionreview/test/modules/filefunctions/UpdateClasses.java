/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filefunctions;

import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.MainTask;
import com.heinvdende.versionreview.test.modules.repository.domain.User;

/**
 *
 * @author Heinrich
 */
public interface UpdateClasses {
    public MainTask addClass(CodeFile file, MainTask task);
}
