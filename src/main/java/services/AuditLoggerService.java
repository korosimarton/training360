package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import daos.AuditLog;
import daos.AuditLoggerRepository;
import daos.LocationsRepository;

@Service
public class AuditLoggerService {

    AuditLoggerRepository auditLoggerRepository;

    public AuditLoggerService(AuditLoggerRepository auditLoggerRepository) {
        this.auditLoggerRepository = auditLoggerRepository;
    }

    @Transactional
    public void saveAuditLog(AuditLog auditLog){
        auditLoggerRepository.save(auditLog);
    }

    public List<AuditLog> listAuditLogs(){
        return  auditLoggerRepository.findAll();
    }
}
