package com.example.CRS.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;



    @Entity
    @Data
    public class Student extends User {
        private String name;
        private double cgpa;
        private double tenthPercent;
        private double twelfthPercent;
        private double diplomaPercent;
        private LocalDate dob;
        private String internshipDetails;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getCgpa() {
            return cgpa;
        }

        public void setCgpa(double cgpa) {
            this.cgpa = cgpa;
        }

        public double getTenthPercent() {
            return tenthPercent;
        }

        public void setTenthPercent(double tenthPercent) {
            this.tenthPercent = tenthPercent;
        }

        public double getTwelfthPercent() {
            return twelfthPercent;
        }

        public void setTwelfthPercent(double twelfthPercent) {
            this.twelfthPercent = twelfthPercent;
        }

        public double getDiplomaPercent() {
            return diplomaPercent;
        }

        public void setDiplomaPercent(double diplomaPercent) {
            this.diplomaPercent = diplomaPercent;
        }

        public LocalDate getDob() {
            return dob;
        }

        public void setDob(LocalDate dob) {
            this.dob = dob;
        }

        public String getInternshipDetails() {
            return internshipDetails;
        }

        public void setInternshipDetails(String internshipDetails) {
            this.internshipDetails = internshipDetails;
        }

    }


