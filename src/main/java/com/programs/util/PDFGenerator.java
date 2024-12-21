// PDFGenerator Utility program

package main.java.com.programs.util;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;
import main.java.com.programs.dao.*;
import main.java.com.programs.model.*;

public class PDFGenerator
{
    public static void generatePDF(Connection connection, int personalID) throws Exception
    {
        PersonalInfoDAO personalInfoDAO = new PersonalInfoDAO();
        PersonalInfo personalInfo = personalInfoDAO.getPersonalInfoById(connection, personalID)
                .orElseThrow(() -> new Exception("Personal Info not found"));

        String userHome = System.getProperty("user.home");
        String downloadsPath = Paths.get(userHome, "Downloads").toString();
        String outputPath = Paths.get(downloadsPath, personalInfo.getFName() + personalInfo.getLName() + "-Resume.pdf").toString();

        try (PdfWriter writer = new PdfWriter(new FileOutputStream(new File(outputPath)));
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer)))
            {

                PdfFont boldFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);

                // Personal Information Section
                addSectionHeader(document, "Resume", boldFont, 20);
                addSectionHeader(document, "Personal Information", boldFont, 14);
                document.add(new Paragraph("First Name: " + personalInfo.getFName()));
                document.add(new Paragraph("Last Name: " + personalInfo.getLName()));
                document.add(new Paragraph("Email: " + personalInfo.getEmail()));
                document.add(new Paragraph("Phone: " + personalInfo.getPhone()));
                document.add(new Paragraph("Address: " + personalInfo.getAddress()));
                document.add(new Paragraph("City: " + personalInfo.getCity()));
                document.add(new Paragraph("State: " + personalInfo.getState()));
                document.add(new Paragraph("Zip Code: " + personalInfo.getZip()));
                document.add(new Paragraph("Country: " + personalInfo.getCountry()));

                // Education Section
                addEducationSection(document, connection, personalID, boldFont);

                // Experience Section
                addExperienceSection(document, connection, personalID, boldFont);

                // Certifications Section
                addCertificationsSection(document, connection, personalID, boldFont);

                // Projects Section
                addProjectsSection(document, connection, personalID, boldFont);

                // Skills Section
                addSkillsSection(document, connection, personalID, boldFont);

                // Achievements Section
                addAchievementsSection(document, connection, personalID, boldFont);
            }
    }

    private static void addEducationSection(Document document, Connection connection, int personalID, PdfFont boldFont) throws Exception
    {
        EducationDAO educationDAO = new EducationDAO();
        List<Education> educationList = educationDAO.getEducation(connection, personalID).stream()
                .filter(e -> !e.getDegree().isEmpty() && !e.getInstitution().isEmpty())
                .distinct()
                .collect(Collectors.toList());
        addSectionHeader(document, "Education", boldFont, 14);
        for (Education education : educationList)
        {
            document.add(new Paragraph("Degree: " + education.getDegree()));
            document.add(new Paragraph("Institution: " + education.getInstitution()));
            document.add(new Paragraph("Major: " + education.getMajor()));
            document.add(new Paragraph("GPA: " + education.getGPA()));
            document.add(new Paragraph("Start Date: " + education.getStartDate()));
            document.add(new Paragraph("End Date: " + education.getEndDate()));
        }
    }

    private static void addExperienceSection(Document document, Connection connection, int personalID, PdfFont boldFont) throws Exception
    {
        ExperienceDAO experienceDAO = new ExperienceDAO();
        List<Experience> experienceList = experienceDAO.getExperience(connection, personalID).stream()
                .filter(e -> !e.getJobTitle().isEmpty() && !e.getCompanyName().isEmpty())
                .distinct()
                .collect(Collectors.toList());
        addSectionHeader(document, "Experience", boldFont, 14);
        for (Experience experience : experienceList)
        {
            document.add(new Paragraph("Job Title: " + experience.getJobTitle()));
            document.add(new Paragraph("Company: " + experience.getCompanyName()));
            document.add(new Paragraph("Description: " + experience.getDescription()));
            document.add(new Paragraph("Start Date: " + experience.getStartDate()));
            document.add(new Paragraph("End Date: " + experience.getEndDate()));
        }
    }

    private static void addCertificationsSection(Document document, Connection connection, int personalID, PdfFont boldFont) throws Exception
    {
        CertificationsDAO certificationsDAO = new CertificationsDAO();
        List<Certifications> certificationsList = certificationsDAO.getCertifications(connection, personalID).stream()
                .filter(c -> !c.getCertification().isEmpty() && !c.getOrganization().isEmpty())
                .distinct()
                .collect(Collectors.toList());
        addSectionHeader(document, "Certifications", boldFont, 14);
        for (Certifications certification : certificationsList)
        {
            document.add(new Paragraph("Certification: " + certification.getCertification()));
            document.add(new Paragraph("Organization: " + certification.getOrganization()));
            document.add(new Paragraph("Issue Date: " + certification.getIssueDate()));
            document.add(new Paragraph("Expiration Date: " + certification.getExpirationDate()));
        }
    }

    private static void addProjectsSection(Document document, Connection connection, int personalID, PdfFont boldFont) throws Exception
    {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        List<Projects> projectsList = projectsDAO.getProjects(connection, personalID).stream()
                .filter(p -> !p.getProjectName().isEmpty() && !p.getDescription().isEmpty())
                .distinct()
                .collect(Collectors.toList());
        addSectionHeader(document, "Projects", boldFont, 14);
        for (Projects project : projectsList)
        {
            document.add(new Paragraph("Project Name: " + project.getProjectName()));
            document.add(new Paragraph("Tools: " + project.getTools()));
            document.add(new Paragraph("Description: " + project.getDescription()));
            document.add(new Paragraph("Start Date: " + project.getStartDate()));
            document.add(new Paragraph("End Date: " + project.getEndDate()));
        }
    }

    private static void addSkillsSection(Document document, Connection connection, int personalID, PdfFont boldFont) throws Exception
    {
        SkillsDAO skillsDAO = new SkillsDAO();
        List<Skills> skillsList = skillsDAO.getSkills(connection, personalID).stream()
                .filter(s -> !s.getSkill().isEmpty())
                .distinct()
                .collect(Collectors.toList());
        addSectionHeader(document, "Skills", boldFont, 14);
        for (Skills skill : skillsList)
        {
            document.add(new Paragraph("Skill: " + skill.getSkill()));
            document.add(new Paragraph("Description: " + skill.getSkillDescription()));
            document.add(new Paragraph("Proficiency: " + skill.getProficiency()));
        }
    }

    private static void addAchievementsSection(Document document, Connection connection, int personalID, PdfFont boldFont) throws Exception
    {
        AchievementsDAO achievementsDAO = new AchievementsDAO();
        List<Achievements> achievementsList = achievementsDAO.getAchievements(connection, personalID).stream()
                .filter(a -> !a.getAchievement().isEmpty())
                .distinct()
                .collect(Collectors.toList());
        addSectionHeader(document, "Achievements", boldFont, 14);
        for (Achievements achievement : achievementsList)
        {
            document.add(new Paragraph("Achievement: " + achievement.getAchievement()));
            document.add(new Paragraph("Description: " + achievement.getAchievementDescription()));
            document.add(new Paragraph("Date: " + achievement.getAchievementDate()));
        }
    }

    private static void addSectionHeader(Document document, String title, PdfFont font, int fontSize)
    {
        document.add(new LineSeparator(new SolidLine()).setMarginTop(10).setMarginBottom(10));
        document.add(new Paragraph(title).setFont(font).setFontSize(fontSize));
        document.add(new LineSeparator(new SolidLine()).setMarginTop(10).setMarginBottom(10));
    }
}
