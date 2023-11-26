package com.example.dataservice.student;

import com.example.controllerlibs.ReportFilter;
import com.example.datastore.IDataStoreRetrivable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;

/**
 * Interface for Student's access to Report generation.
 */
public interface IStudentReportDBService {
    public IDataStoreEditOperation<Camp> DSGenerateParticipantReport(String fileName, IDataStoreRetrivable<Student> studentDSRetrivable, ReportFilter reportFilter);
}
