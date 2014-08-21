/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filefunctions;

import java.io.File;

/**
 *
 * @author Heinrich
 */

public interface GetCommittedFileService {
    public String getFinalFile(String fileName);
    public String getCommittedFile(String file);
    public boolean isRepositoryUsed();
}
