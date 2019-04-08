
package my.suveng.server.common.poi.export;


import my.suveng.server.common.exceptions.BusinessStateException;
import my.suveng.server.common.exceptions.NestedExceptionUtils;
import my.suveng.server.common.poi.core.ClassMetaResolver;
import my.suveng.server.common.poi.core.POIEntityMeta;
import my.suveng.server.common.poi.core.POIEntityMetaCache;
import my.suveng.server.common.poi.core.POIException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @version 1.0.0
 * @description POI增量导出（特别注意，该类是非线程安全的，如果需要在多线程环境下使用，请自行处理同步问题）
 * @since 2016-07-13
 */
public class POIIncrementExport extends AbstractPOIExport {

    /**
     * 模板文件流
     */
    private InputStream in = null;

    /**
     * 实体元数据
     */
    private POIEntityMeta poiEntityMeta = null;

    /**
     * 属性s
     */
    private List<String> props = null;

    private SXSSFWorkbook workbook = null;
    private SXSSFSheet sheet = null;

    public POIIncrementExport(Class<?> clazz) throws POIException {
        createWorkbook(clazz);
        createTableHead();
    }

    public POIIncrementExport(Class<?> clazz, List<String> props) throws POIException {
        this.props = props;
        if (this.props == null || props.size() == 0) {
            throw new BusinessStateException("props is null or props.size() is 0");
        }

        createWorkbook(clazz);
        createTableHead();
    }

    /**
     * 创建Excel workbook
     *
     * @param clazz
     *
     * @throws IOException
     */
    private void createWorkbook(Class<?> clazz) throws POIException {
        if (!ClassMetaResolver.isExcelEntity(clazz)) {
            throw new POIException(clazz + " is not ExcelEntity.");
        }

        poiEntityMeta = POIEntityMetaCache.getEntityMeta(clazz);
        if (StringUtils.isEmpty(poiEntityMeta.getTempletPath())) {
            throw new POIException("TempletPath is null.");
        }

        try {
            String templetFilePath = getClass().getClassLoader().getResource("export/poi_templet.xls").getPath();
            in = new FileInputStream(templetFilePath);

            workbook = new SXSSFWorkbook();
            sheet = (SXSSFSheet) workbook.createSheet();
            workbook.setActiveSheet(poiEntityMeta.getSheet());
			/*sheet = (SXSSFSheet) workbook.getSheetAt(poiEntityMeta.getSheet());
			workbook.setActiveSheet(poiEntityMeta.getSheet());*/
        } catch (Exception e) {
            throw new POIException(NestedExceptionUtils.buildMessage("createWorkbook exception.", e));
        }
    }

    /**
     * 创建表头
     *
     * @return int
     */
    private int createTableHead() throws POIException {
        return createTableHead(poiEntityMeta, this.props, workbook, sheet, 0);
    }

    /**
     * 创建表体
     *
     * @param entitys
     *
     * @return
     *
     * @throws IllegalAccessException int
     */
    public int createTableBody(List<?> entitys) throws POIException {
        return createTableBody(entitys, this.props, poiEntityMeta, workbook, sheet);
    }

    /**
     * 将文件写到流里面
     *
     * @param response
     * @param fileName void
     */
    public void writeAndFlush(HttpServletResponse response, String fileName) throws POIException {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new POIException(NestedExceptionUtils.buildMessage("writeAndFlush exception.", e));
        } finally {
            close();
            sheet = null;
            workbook = null;
        }
    }

    /**
     * 将文件写到流里面
     *
     * @throws IOException void
     */
    public void writeAndFlush(OutputStream out) throws POIException {
        try {
            workbook.write(out);
            out.flush();
        } catch (Exception e) {
            throw new POIException(NestedExceptionUtils.buildMessage("writeAndFlush exception.", e));
        } finally {
            close();
        }
    }

    /**
     * 关闭文件流
     */
    public void close() {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                throw new POIException(NestedExceptionUtils.buildMessage("close exception.", e));
            }
        }
    }

    /**
     * 重新设置sheet页
     */
    @Override
    protected void resetSheet(SXSSFSheet sheet) {
        this.sheet = sheet;
    }

    public static void main(String[] args) throws Exception {
//		Jws.applicationPath = new File("E:\\ECWorkspace\\coder\\src\\main\\resources");
//
//		OutputStream out = new FileOutputStream("D:\\a2.xls");
//
//		List<String> props = new ArrayList<String>();
//		props.add("ageint");
//		props.add("nameString");
//
//		POIIncrementExport export = new POIIncrementExport(User.class, props);
//		int w = 1;
//		for(int j = 0; j < 5; j++) {
//			List<User> entitys = new ArrayList<User>();
//			for(int i = 0; i < 20000; i++) {
//				User u = new User();
//				u.setAgeint(100 + w);
//				u.setDate(new Date());
//				u.setDoubledouble(200000D);
//				u.setFloatfloat(2000.00F);
//				u.setNameString("I==="+w);
//				u.setSsShort((short) 1);
//				entitys.add(u);
//				w++;
//			}
//			export.createTableBody(entitys);
//		}
//		export.writeAndFlush(out);
    }
}
