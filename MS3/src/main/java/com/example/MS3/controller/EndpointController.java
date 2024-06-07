package com.example.MS3.controller;

import com.example.MS3.dto.M1Dto;
import com.example.MS3.dto.InfortunioDTO;
import com.example.MS3.service.EndpointService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.opencsv.CSVWriter;

import javax.batch.operations.JobExecutionAlreadyCompleteException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/endpoint")
public class EndpointController {
    @Autowired
    private EndpointService endpointService;

    @Autowired
    private HttpServletRequest request; // Inject HttpServletRequest

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


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

    private void writeCsv(Long endpointId, String method, String path, LocalDateTime date) {
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

    @GetMapping("batch")
    public BatchStatus startBatch() throws JobInstanceAlreadyExistsException
            , JobExecutionAlreadyCompleteException,
            JobParametersInvalidException,
            JobRestartException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("endpointId", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        return jobExecution.getStatus();

    }


    @GetMapping("/infortuni")
    public ResponseEntity<InfortunioDTO> getInfortuni(
            @RequestParam String Regione,
            @RequestParam int AnnoAccadimento,
            @RequestParam int MeseAccadimento) {

        String method = request.getMethod();
        String path = request.getRequestURI();
        LocalDateTime date = LocalDateTime.now();
        long endpointId = endpointCounter.getAndIncrement();

        writeCsv(endpointId, method, path, date);

        return endpointService.getInfortuni(Regione, AnnoAccadimento, MeseAccadimento);
    }




}
