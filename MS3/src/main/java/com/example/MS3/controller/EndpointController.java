package com.example.MS3.controller;

import com.example.MS3.dto.M1Dto;
import com.example.MS3.service.EndpointService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.opencsv.CSVWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/endpoint")
public class EndpointController {

    @Autowired
    private HttpServletRequest request; // Inject HttpServletRequest

    @Autowired
    private EndpointService endpointService;

    private static final AtomicInteger endpointCounter = new AtomicInteger(1);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @GetMapping
    public List<M1Dto> getBaw1() {

        String method = request.getMethod();
        String path = request.getRequestURI();
        LocalDateTime date = LocalDateTime.now();
        long endpointId = endpointCounter.getAndIncrement();

        writeCsv(endpointId, method, path, date);

        return endpointService.getBaw1();
    }

    private void writeCsv(long endpointId, String method, String path, LocalDateTime date) {
        String filePath = "MS3/src/main/resources/endpoint.csv";
        File file = new File(filePath);
        boolean isNewFile = !file.exists();

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {
            if (isNewFile) {
                // Write header
                String[] header = {"endpointId", "endpointPath", "endpointType", "endpointDate"};
                writer.writeNext(header);
            }

            String formattedDate = date.format(formatter);
            String[] data = {String.valueOf(endpointId), path, method, formattedDate};
            writer.writeNext(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
