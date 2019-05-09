package cn.dym.outman.solr.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Collation;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Correction;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import cn.dym.outman.solr.bean.Person;

public class SolrUtil {

    /**
     * solr http服务地址
     */
    public static String SOLR_URL = "http://127.0.0.1:8080/solr";

    /**
     * solr的core
     */
    public static String SOLR_CORE = "articles";

   /* static {
        Properties properties = new Properties();
        String path = SolrUtil.class.getClassLoader().getResource("solr.properties").getPath(); 
//        		SolrUtil.class.getResource("/").getFile().toString()
//                + "classpath:solr.properties";
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            properties.load(fis);
            SOLR_URL = properties.getProperty("SOLR_URL");
            SOLR_CORE = properties.getProperty("SOLR_CORE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 主函数入口
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 1.测试插入文档
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("id", "00007");
//        map.put("name", "dym4");
//        map.put("age", "29");
//        map.put("addr", "郑州4");
//        addDocument(map, SOLR_CORE);

        // 2.通过bean添加document
        /*List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("00002", "lisi", 25, "重庆"));
        persons.add(new Person("00003", "wangwu", 26, "上海"));
        addDocumentByBean(persons, SOLR_CORE);*/

        // 3.根据id集合删除索引
       /* List<String> ids = new ArrayList<String>();
        ids.add("00001");
        ids.add("00002");
        ids.add("00003");
        deleteDocumentByIds(ids, SOLR_CORE);*/

        // 4.查询
        getDocument(SOLR_CORE);

        // 5.spell测试
//        getSpell(SOLR_CORE);

    }

    /**
     * 获取solr服务
     * 
     * @return
     */
    private static HttpSolrClient getSolrClient(String core) {
    	
        return new HttpSolrClient.Builder(SOLR_URL+core)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }

    /**
     * 添加文档
     * 
     * @param map
     * @param core
     * @throws Exception
     */
    private static void addDocument(Map<String, String> map, String core)
            throws Exception {
        SolrInputDocument sid = new SolrInputDocument();
        for (Entry<String, String> entry : map.entrySet()) {
            sid.addField(entry.getKey(), entry.getValue());
        }
        HttpSolrClient solrClient = getSolrClient("/" + core);
        solrClient.add(sid);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 添加文档，通过bean方式
     * 
     * @param persons
     * @param core
     * @throws Exception
     */
    private static void addDocumentByBean(List<Person> persons, String core)
            throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        solrClient.addBeans(persons);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 根据id集合删除索引
     * 
     * @param ids
     * @param core
     * @throws Exception
     */
    private static void deleteDocumentByIds(List<String> ids, String core)
            throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        solrClient.deleteById(ids);
        commitAndCloseSolr(solrClient);
    }

    private static void getDocument(String core) throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        SolrQuery sq = new SolrQuery();

        // q查询
        sq.set("q", "name:*dym*");

        // filter查询
//        sq.addFilterQuery("id:[0 TO 00003]");

        // 排序
        sq.setSort("id", SolrQuery.ORDER.asc);

        // 分页 从第0条开始取，取一条
        sq.setStart(0);
        sq.setRows(10);

        // 设置高亮
        sq.setHighlight(true);

        // 设置高亮的字段
        sq.addHighlightField("name");

        // 设置高亮的样式
        sq.setHighlightSimplePre("<font color='red'>");
        sq.setHighlightSimplePost("</font>");

        QueryResponse result = solrClient.query(sq);

        // 这里可以从result获得查询数据(两种方式如下)

        // 1.获取document数据
        System.out.println("1.获取document数据-------------------------");
        SolrDocumentList results = result.getResults();
        // 获取查询的条数
        System.out.println("一共查询到" + results.getNumFound() + "条记录");
        for (SolrDocument solrDocument : results) {
        	System.out.println(solrDocument.toString());
//            System.out.println("id:" + solrDocument.get("id"));
//            System.out.println("name:" + solrDocument.get("name"));
//            System.out.println("age:" + solrDocument.get("age"));
//            System.out.println("addr:" + solrDocument.get("addr"));
        }

        // 2.获取对象信息,需要传入对应对象的类class
       /* System.out.println("2.获取对象信息,需要传入对应对象的类class-----------");
        List<Person> persons = result.getBeans(Person.class);
        System.out.println("一共查询到" + persons.size() + "条记录");
        for (Person person : persons) {
            System.out.println(person);
        }
        commitAndCloseSolr(solrClient);*/
    }

    /**
     * 查询使用spell接口，输入错误，solr可以给出建议词
     * 
     * @param core
     * @throws Exception
     */
    private static void getSpell(String core) throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        SolrQuery sq = new SolrQuery();
        sq.set("qt", "/spell");

        // 原本是lisi，这里拼写错误，测试solr返回建议词语
        sq.set("q", "liss");
        QueryResponse query = solrClient.query(sq);
        SolrDocumentList results = query.getResults();

        // 获取查询条数
        long count = results.getNumFound();

        // 判断是否查询到
        if (count == 0) {
            SpellCheckResponse spellCheckResponse = query
                    .getSpellCheckResponse();
            List<Collation> collatedResults = spellCheckResponse
                    .getCollatedResults();
            for (Collation collation : collatedResults) {
                long numberOfHits = collation.getNumberOfHits();
                System.out.println("建议条数为:" + numberOfHits);

                List<Correction> misspellingsAndCorrections = collation
                        .getMisspellingsAndCorrections();
                for (Correction correction : misspellingsAndCorrections) {
                    String source = correction.getOriginal();
                    String current = correction.getCorrection();
                    System.out.println("推荐词语为：" + current + "原始的输入为：" + source);
                }
            }
        } else {
            for (SolrDocument solrDocument : results) {
                // 获取key集合
                Collection<String> fieldNames = solrDocument.getFieldNames();

                // 根据key集合输出value
                for (String field : fieldNames) {
                    System.out.println("key: " + field + ",value: "
                            + solrDocument.get(field));
                }
            }
        }

        // 关闭连接
        commitAndCloseSolr(solrClient);
    }

    /**
     * 提交以及关闭服务
     * 
     * @param solrClient
     * @throws Exception
     */
    private static void commitAndCloseSolr(HttpSolrClient solrClient)
            throws Exception {
        solrClient.commit();
        solrClient.close();
    }

}