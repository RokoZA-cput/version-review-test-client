/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker;

import com.heinvdende.versionreview.test.client.domain.FileChange;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface GetFileChangesService {
    public List<FileChange> getFileChanges();
}
