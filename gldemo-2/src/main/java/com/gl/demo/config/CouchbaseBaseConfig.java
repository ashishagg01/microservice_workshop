/**
 * 
 */
package com.gl.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.cluster.ClusterInfo;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

/**
 * The Class CouchbaseBaseConfig.
 *
 * @author vikas.kumar3
 */
@EnableCouchbaseRepositories(basePackages="com.gl.demo.repo")
@Configuration
public class CouchbaseBaseConfig extends AbstractCouchbaseConfiguration {
        
    @Override
    protected CouchbaseEnvironment getEnvironment() {
      DefaultCouchbaseEnvironment.builder().connectTimeout(60000) // by default 5 sec (5000 ms)
          .queryTimeout(20000) // by default 75 sec (75000 ms)
          .socketConnectTimeout(45000); // by default 1 sec (1000 ms)
      return super.getEnvironment();
    }
    
    @Override
	protected List<String> getBootstrapHosts() {
		return Arrays.asList("127.0.0.1");
	}

	@Override
	protected String getBucketName() {
		return "demo2";
	}

	@Override
	protected String getBucketPassword() {
		return "";
	}
    
    /* (non-Javadoc)
     * @see org.springframework.data.couchbase.config.AbstractReactiveCouchbaseConfiguration#couchbaseClusterInfo()
     */
    @Override
    @Bean(name = BeanNames.COUCHBASE_CLUSTER_INFO)
    public ClusterInfo couchbaseClusterInfo() throws Exception {
      return couchbaseCluster().authenticate("Administrator", "Administrator").clusterManager()
          .info();
    }

    /* (non-Javadoc)
     * @see org.springframework.data.couchbase.config.AbstractReactiveCouchbaseConfiguration#couchbaseClient()
     */
    @Override
    @Bean(destroyMethod = "close", name = BeanNames.COUCHBASE_BUCKET)
    public Bucket couchbaseClient() throws Exception {
      // @Bean method can use another @Bean method in the same @Configuration by
      // directly invoking it
      return couchbaseCluster().openBucket(getBucketName());
    }
    
}
