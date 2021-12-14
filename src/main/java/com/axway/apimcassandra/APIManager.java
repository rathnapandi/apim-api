package com.axway.apimcassandra;

import com.axway.apimcassandra.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.BeanPropertyRowMapper;
import org.springframework.data.cassandra.core.cql.CqlOperations;

import java.util.ArrayList;
import java.util.List;


public class APIManager {

    private  final  Logger logger = LoggerFactory.getLogger(APIManager.class);
    @Autowired
    private CassandraOperations cassandraTemplate;

//    public FrontendAPI listProxiesAndAccessByAPIName(String apiName) {
//        CqlOperations cqlOperations = cassandraTemplate.getCqlOperations();
//        apiName = "\"" + apiName + "\"";
//        FrontendAPI frontendAPI = cqlOperations.queryForObject("select * from api_portal_portalvirtualizedapi where name=?", new BeanPropertyRowMapper<>(FrontendAPI.class), apiName);
//        logger.info("{}", frontendAPI);
//        return frontendAPI;
//        //apiAccess(frontendAPI);
//    }

    public List<String> getOrganizationNames(){
        CqlOperations cqlOperations = cassandraTemplate.getCqlOperations();

        List<String> orgNames = cqlOperations.query(
                session -> session.prepare("SELECT \"organizationDn\" from api_portal_portalorganizationstore"), (row, rowNum) -> row.getString(0));
        return orgNames;

    }
    public Summary getSummary(){
        CqlOperations cqlOperations = cassandraTemplate.getCqlOperations();
        long frontendAPICount = cqlOperations.queryForObject("SELECT COUNT(*) FROM api_portal_portalvirtualizedapi", Long.class);
        long backendAPICount = cqlOperations.queryForObject("SELECT COUNT(*) FROM api_portal_portalapi", Long.class);
        long organizationsCount = cqlOperations.queryForObject("SELECT COUNT(*) FROM api_portal_portalorganizationstore", Long.class);
        long applicationsCount = cqlOperations.queryForObject("SELECT COUNT(*) FROM api_server_portalapplicationstore", Long.class);
        long usersCount = cqlOperations.queryForObject("SELECT COUNT(*) FROM api_portal_portaluserstoreldap", Long.class);
        long quotasCount =  cqlOperations.queryForObject("SELECT COUNT(*) FROM api_portal_portalapiquotaconstraintstore", Long.class);

        Summary summary = new Summary();
        summary.setFrontendAPICount((int)frontendAPICount);
        summary.setBackendAPICount((int)backendAPICount);
        summary.setOrganizationCount((int)organizationsCount);
        summary.setApplicationCount((int)applicationsCount);
        summary.setUsersCount((int)usersCount);
        summary.setQuotasCount((int)quotasCount);
        return summary;

    }

    public APIDependency getProxyDependencyByName(String apiName){
        CqlOperations cqlOperations = cassandraTemplate.getCqlOperations();
        apiName = "\"" + apiName + "\"";

        FrontendAPI frontendAPI = cqlOperations.queryForObject("select * from api_portal_portalvirtualizedapi where name=?", new BeanPropertyRowMapper<>(FrontendAPI.class), apiName);
        logger.info("{}", frontendAPI);
        List<AccessStore> accessStores = cqlOperations.query("select * from api_portal_portalapiaccessstore where \"apiId\"=?", new BeanPropertyRowMapper<>(AccessStore.class), frontendAPI.getId());
        logger.info("{}", accessStores.size());
        List<Organization> providerOrganizations = new ArrayList<>();
        List<Organization> consumerOrganizations = new ArrayList<>();
        List<Application> applications = new ArrayList<>();
        String providerOrg = frontendAPI.getOrganizationId();
        for (AccessStore accessStore : accessStores) {
          //  logger.info("{}", accessStore);
            String entityId = accessStore.getEntityId();
            if(entityId.equals(accessStore.getOrganizationId())){
                logger.info("Entity id : {} Belong to  organization", entityId);
                Organization organization = cqlOperations.queryForObject("select \"organizationDn\",development,enabled from api_portal_portalorganizationstore where key=?", new BeanPropertyRowMapper<>(Organization.class), entityId.replaceAll("\"",""));
              //  logger.info("{}", organization);
                if(entityId.equals(providerOrg))
                    providerOrganizations.add(organization);
                else
                    consumerOrganizations.add(organization);
            }else {
                Application application = cqlOperations.queryForObject("select * from api_server_portalapplicationstore where id=?", new BeanPropertyRowMapper<>(Application.class), entityId);
               // logger.info("{}", application);
                applications.add(application);
            }
        }
        APIDependency apiDependency = new APIDependency();
        apiDependency.setName(unquote(frontendAPI.getName(), '"'));
        apiDependency.setId(unquote(frontendAPI.getId(), '"'));
        apiDependency.setApplications(applications);
        apiDependency.setProviderOrganizations(providerOrganizations);
        apiDependency.setConsumerOrganizations(consumerOrganizations);
        return  apiDependency;
    }

    private static boolean isQuoted(String value, char quoteChar) {
        return value != null && value.length() > 1
                && value.charAt(0) == quoteChar && value.charAt(value.length() - 1) == quoteChar;
    }

    private static String emptyQuoted(char quoteChar) {
        // don't handle non quote characters, this is done so that these are interned and don't create
        // repeated empty quoted strings.
        assert quoteChar == '"' || quoteChar == '\'';
        if (quoteChar == '"')
            return "\"\"";
        else
            return "''";
    }

    private static String unquote(String text, char quoteChar) {
        if (!isQuoted(text, quoteChar))
            return text;

        if (text.length() == 2)
            return "";

        String search = emptyQuoted(quoteChar);
        int nbMatch = 0;
        int start = -1;
        do {
            start = text.indexOf(search, start + 2);
            // ignore the second to last character occurrence, as the last character is a quote.
            if (start != -1 && start != text.length() - 2)
                ++nbMatch;
        } while (start != -1);

        // no escaped quotes found, simply remove surrounding quotes and return.
        if (nbMatch == 0)
            return text.substring(1, text.length() - 1);

        // length of the new string will be its current length - the number of occurrences.
        int newLength = text.length() - nbMatch - 2;
        char[] result = new char[newLength];
        int newIdx = 0;
        // track whenever a quoteChar is encountered and the previous character is not a quoteChar.
        boolean firstFound = false;
        for (int i = 1; i < text.length() - 1; i++) {
            char c = text.charAt(i);
            if (c == quoteChar) {
                if (firstFound) {
                    // The previous character was a quoteChar, don't add this to result, this action in
                    // effect removes consecutive quotes.
                    firstFound = false;
                } else {
                    // found a quoteChar and the previous character was not a quoteChar, include in result.
                    firstFound = true;
                    result[newIdx++] = c;
                }
            } else {
                // non quoteChar encountered, include in result.
                result[newIdx++] = c;
                firstFound = false;
            }
        }
        return new String(result);
    }



//    public List<FrontendAPI> listProxies(){
//            CqlOperations cqlOperations = cassandraTemplate.getCqlOperations();
//            List<FrontendAPI> frontendAPI = cqlOperations.query("select * from api_portal_portalvirtualizedapi", new BeanPropertyRowMapper<>(FrontendAPI.class));
//            logger.info("{}", frontendAPI.size());
//            return frontendAPI;
//    }

//    private void apiAccess(FrontendAPI frontendAPI){
//        CqlOperations cqlOperations = cassandraTemplate.getCqlOperations();
//        List<AccessStore> accessStores = cqlOperations.query("select * from api_portal_portalapiaccessstore where \"apiId\"=?", new BeanPropertyRowMapper<>(AccessStore.class), frontendAPI.getId());
//        logger.info("{}", accessStores.size());
//        for (AccessStore accessStore : accessStores) {
//            logger.info("{}", accessStore);
//            String entityId = accessStore.getEntityId();
//            if(entityId.equals(accessStore.getOrganizationId())){
//                logger.info("Entity id : {} Belong to  organization", entityId);
//                Organization organization = cqlOperations.queryForObject("select \"organizationDn\",development,enabled from api_portal_portalorganizationstore where key=?", new BeanPropertyRowMapper<>(Organization.class), entityId.replaceAll("\"",""));
//                logger.info("{}", organization);
//            }else {
//                Application application = cqlOperations.queryForObject("select * from api_server_portalapplicationstore where id=?", new BeanPropertyRowMapper<>(Application.class), entityId);
//                logger.info("{}", application);
//            }
//        }
//    }
}
