package com.faust8888.project.dictionary.utils;


import com.faust8888.project.dictionary.exception.DictionaryInvalidFormatRuntimeException;
import com.faust8888.project.dictionary.viewItems.DictionaryView;
import com.faust8888.project.dictionary.viewItems.WordView;
import com.faust8888.project.dictionary.viewItems.WordViewBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcelUtils {

    private static final String[] HEADERS = new String[]{"WORD", "MEANING", "TRANSLATE", "EXAMPLE"};
    private static final int SHEET_WORDS_LIMIT = 500;

    private static final int WORD_COLUMN = 0;
    private static final int MEANING_COLUMN = 1;
    private static final int TRANSLATE_COLUMN = 2;
    private static final int CONTEXT_COLUMN = 3;

    public static Workbook createWorkbook(final DictionaryView dictionary) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        populateWorkbook(workbook, dictionary);
        return workbook;
    }

    private static void populateWorkbook(final Workbook workbook, final DictionaryView dictionary) {
        Lists.partition(dictionary.getWordList(), SHEET_WORDS_LIMIT)
                .stream()
                .forEach(subList -> createSheet(workbook, subList));
    }

    public static DictionaryView read(final String fileName) throws Exception {
        Iterator<Sheet> sheetIterator = createSheetIterator(fileName);

        Map<String, WordView> wordMap = new HashMap<>();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();

            //TODO: need to change in the future
            if(sheet.getSheetName().equals("Phrases") || sheet.getSheetName().equals("Topics")) {
                continue;
            }

            Map<String, WordView> oneSheetWordMap = createMapWord(sheet);
            wordMap.putAll(oneSheetWordMap);
        }
        return new DictionaryView(fileName, wordMap);
    }

    private static Map<String, WordView> createMapWord(final Sheet sheet) {
        Iterator<Row> rowIterator = sheet.rowIterator();
        return readRows(rowIterator, sheet.getPhysicalNumberOfRows());
    }

    private static Map<String, WordView> readRows(final Iterator<Row> rowIterator,final int countRows) {
        Map<String,WordView> mapWord = new HashMap<>(countRows);
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            WordView wordView = createWord(row);
            if(mapWord.containsKey(wordView.getWord())) {
                continue;
            }
            mapWord.put(wordView.getWord(), wordView);
        }
        return mapWord;
    }

    private static WordView createWord(final Row row) {
        WordViewBuilder wordViewBuilder = new WordViewBuilder();

        int count = 0;
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String value = cell.getStringCellValue();

            switch (count) {
                case WORD_COLUMN:
                    wordViewBuilder.setWord(value);
                    break;
                case MEANING_COLUMN:
                    wordViewBuilder.setMeaning(value);
                    break;
                case TRANSLATE_COLUMN:
                    wordViewBuilder.setTranslate(value);
                    break;
                case CONTEXT_COLUMN:
                    wordViewBuilder.setContext(value);
                    break;
                default:
                    throw new DictionaryInvalidFormatRuntimeException("Invalid DictionaryView Format.");
            }
            count++;
            if(wordViewBuilder.isWordReady()) {
                break;
            }
        }
        return wordViewBuilder.buildWord();
    }

    private static Iterator<Sheet> createSheetIterator(final String fileName) throws Exception{
        FileInputStream excelFile = new FileInputStream(new File(fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        return workbook.sheetIterator();
    }

    private static void createSheet(final Workbook workbook, final List<WordView> wordViewList) {
        String sheetName = "Page_" + workbook.getNumberOfSheets();
        Sheet sheet = workbook.createSheet(sheetName);
        createHeaderRow(sheet);
        wordViewList.forEach(wordView -> createRow(sheet, wordView));
    }

    private static void createRow(final Sheet sheet, final WordView wordView) {
        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);
        createCells(row, wordView);
    }

    private static void createCells(final Row row, final WordView wordView) {
        int celNum = -1;

        Cell cellWord = row.createCell(++celNum);
        cellWord.setCellValue(wordView.getWord());

        Cell cellMeaning = row.createCell(++celNum);
        cellMeaning.setCellValue(wordView.getMeaning());

        Cell cellTranslate = row.createCell(++celNum);
        cellTranslate.setCellValue(wordView.getTranslate());

        Cell cellContext = row.createCell(++celNum);
        cellContext.setCellValue(wordView.getContext());
    }

    private static void createHeaderRow(final Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        AtomicInteger cellNum = new AtomicInteger(-1);
        Arrays.stream(HEADERS).forEach(header -> {
            Cell cell = headerRow.createCell(cellNum.incrementAndGet());
            cell.setCellValue(header);
        });
    }
}
