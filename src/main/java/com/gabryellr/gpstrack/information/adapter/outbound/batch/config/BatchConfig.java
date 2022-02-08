package com.gabryellr.gpstrack.information.adapter.outbound.batch.config;

import com.gabryellr.gpstrack.information.adapter.outbound.batch.model.GpsInformationDto;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job job(JobBuilderFactory factory, StepBuilderFactory stepFactory,
        ItemReader<GpsInformationDto> itemReader,
        ItemProcessor<GpsInformationDto, GpsInformation> itemProcessor,
        ItemWriter<GpsInformation> itemWriter) {

        Step step = stepFactory.get("ETL-file-load")
                               .<GpsInformationDto, GpsInformation>chunk(100)
                               .reader(itemReader)
                               .processor(itemProcessor)
                               .writer(itemWriter)
                               .faultTolerant().skipPolicy(new SkipPolicyConfig()).skip(NumberFormatException.class)
                               .build();

        return factory.get("ETL-Load")
                      .incrementer(new RunIdIncrementer())
                      .start(step)
                      .build();
    }

    @Bean
    public FlatFileItemReader<GpsInformationDto> fileItemReader() {

        FlatFileItemReader<GpsInformationDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("vehicles.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLineMapper(lineMapper());

        return flatFileItemReader;
    }

    @Bean
    public LineMapper<GpsInformationDto> lineMapper() {
        DefaultLineMapper<GpsInformationDto> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(GpsInformationDto.fields());

        BeanWrapperFieldSetMapper<GpsInformationDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(GpsInformationDto.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
}
