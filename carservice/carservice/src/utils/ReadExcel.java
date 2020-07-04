package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {

//	public static void citanjeXls(String[] args) {
//
//		String fileName = "obrazac.xls";
//		// Read an Excel File and Store in a Vector
//		Vector dataHolder = readExcelFile(fileName);
//		// Print the data read
//		printCellDataToConsole(dataHolder);
//	}
//
//	public static Vector readExcelFile(String fileName) {
//		/**
//		 * --Define a Vector --Holds Vectors Of Cells
//		 */
//		Vector cellVectorHolder = new Vector();
//
//		try {
//			/** Creating Input Stream **/
//			// InputStream myInput= ReadExcelFile.class.getResourceAsStream( fileName );
//			FileInputStream myInput = new FileInputStream(fileName);
//
//			/** Create a POIFSFileSystem object **/
//			POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
//
//			/** Create a workbook using the File System **/
//			HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
//
//			/** Get the first sheet from workbook **/
//			HSSFSheet mySheet = myWorkBook.getSheetAt(0);
//
//			/** We now need something to iterate through the cells. **/
//			Iterator rowIter = mySheet.rowIterator();
//
//			while (rowIter.hasNext()) {
//				HSSFRow myRow = (HSSFRow) rowIter.next();
//				Iterator cellIter = myRow.cellIterator();
//				Vector cellStoreVector = new Vector();
//				while (cellIter.hasNext()) {
//					HSSFCell myCell = (HSSFCell) cellIter.next();
//					cellStoreVector.addElement(myCell);
//				}
//				cellVectorHolder.addElement(cellStoreVector);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cellVectorHolder;
//	}
//
//	private static void printCellDataToConsole(Vector dataHolder) {
//
//		try {
//			for (int i = 0; i < 8000; i++) {
//
//				Vector cellStoreVector = (Vector) dataHolder.elementAt(i);
//
//				for (int j = 0; j < cellStoreVector.size(); j++) {
//					HSSFCell myCell = (HSSFCell) cellStoreVector.elementAt(j);
//					String stringCellValue = myCell.toString();
//					System.out.print(j + " " + stringCellValue + "\t");
//
//				}
//
//				System.out.println();
//
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static void citanjeXls(String[] args) throws IOException {

		File excelFile = new File("obrazac.xls");
		FileInputStream fis = new FileInputStream( excelFile );
		
	}

}