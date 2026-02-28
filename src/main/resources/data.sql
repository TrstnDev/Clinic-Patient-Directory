-- ==========================================
-- 1. Insert Physician Roles
-- ==========================================
INSERT INTO roles (id, role_code, role_name, role_title) VALUES
                                                             (gen_random_uuid(), 'PHYS', 'Attending Physician', 'Dr.'),
                                                             (gen_random_uuid(), 'INT', 'Intern', 'Dr.'),
                                                             (gen_random_uuid(), 'MO', 'Medical Officer', 'Dr.'),
                                                             (gen_random_uuid(), 'SPEC', 'Specialist Physician', 'Dr.'),
                                                             (gen_random_uuid(), 'PROF', 'Head of Department', 'Prof.'),
                                                             (gen_random_uuid(), 'REG', 'Registrar', 'Dr.')
ON CONFLICT (role_code) DO NOTHING;

-- ==========================================
-- 2. Insert Medical Specialties
-- ==========================================
INSERT INTO specialties (id, specialty_code, specialty_title) VALUES
                                                                  (gen_random_uuid(), 'PAEDCARD', 'Paediatric Cardiology'),
                                                                  (gen_random_uuid(), 'NEO', 'Neonatology'),
                                                                  (gen_random_uuid(), 'TRAUM', 'Trauma Surgery'),
                                                                  (gen_random_uuid(), 'GERI', 'Geriatrics'),
                                                                  (gen_random_uuid(), 'ENDO', 'Endocrinology'),
                                                                  (gen_random_uuid(), 'ONC', 'Oncology'),
                                                                  (gen_random_uuid(), 'PSYCH', 'Psychiatry'),
                                                                  (gen_random_uuid(), 'MAX', 'Maxillofacial Surgery'),
                                                                  (gen_random_uuid(), 'EMMED', 'Emergency Medicine'),
                                                                  (gen_random_uuid(), 'DERM', 'Dermatology'),
                                                                  (gen_random_uuid(), 'TOX', 'Toxicology'),
                                                                  (gen_random_uuid(), 'PHARM', 'Clinical Pharmacology'),
                                                                  (gen_random_uuid(), 'ANAES', 'Anaesthesiology'),
                                                                  (gen_random_uuid(), 'PLAST', 'Plastic & Reconstructive Surgery'),
                                                                  (gen_random_uuid(), 'HAEM', 'Haematology'),
                                                                  (gen_random_uuid(), 'RHEUM', 'Rheumatology'),
                                                                  (gen_random_uuid(), 'FAM', 'Family Medicine'),
                                                                  (gen_random_uuid(), 'PATH', 'Pathology'),
                                                                  (gen_random_uuid(), 'IMUN', 'Allergology & Immunology'),
                                                                  (gen_random_uuid(), 'GENE', 'Medical Genetics'),
                                                                  (gen_random_uuid(), 'INF', 'Infectious Diseases'),
                                                                  (gen_random_uuid(), 'NUC', 'Nuclear Medicine'),
                                                                  (gen_random_uuid(), 'RAD', 'Radiology'),
                                                                  (gen_random_uuid(), 'FOR', 'Forensic Medicine'),
                                                                  (gen_random_uuid(), 'OPHTH', 'Ophthalmology'),
                                                                  (gen_random_uuid(), 'ENT', 'Otorhinolaryngology'),
                                                                  (gen_random_uuid(), 'GAST', 'Gastroenterology'),
                                                                  (gen_random_uuid(), 'NEPH', 'Nephrology'),
                                                                  (gen_random_uuid(), 'PULM', 'Pulmonology'),
                                                                  (gen_random_uuid(), 'INTMED', 'Internal Medicine'),
                                                                  (gen_random_uuid(), 'CARTHOR', 'Cardiothoracic Surgery'),
                                                                  (gen_random_uuid(), 'PAEDSUR', 'Paediatric Surgery'),
                                                                  (gen_random_uuid(), 'VASCSUR', 'Vascular Surgery'),
                                                                  (gen_random_uuid(), 'NEURSUR', 'Neurosurgery'),
                                                                  (gen_random_uuid(), 'GENSUR', 'General Surgery'),
                                                                  (gen_random_uuid(), 'URO', 'Urology'),
                                                                  (gen_random_uuid(), 'OBGYN', 'Obstetrics & Gynaecology'),
                                                                  (gen_random_uuid(), 'GP', 'General Practice'),
                                                                  (gen_random_uuid(), 'CARD', 'Cardiology'),
                                                                  (gen_random_uuid(), 'NEUR', 'Neurology'),
                                                                  (gen_random_uuid(), 'ORTHSUR', 'Orthopaedic Surgery'),
                                                                  (gen_random_uuid(), 'PAED', 'Paediatrics')
ON CONFLICT (specialty_code) DO NOTHING;

-- ==========================================
-- 3. Insert Common ICD-10 Diagnoses
-- ==========================================
INSERT INTO diagnoses (id, icd_10_code, diagnosis_name, diagnosis_description) VALUES
                                                                                   (gen_random_uuid(), 'J20.9', 'Acute bronchitis, unspecified', 'Inflammation of the large airways that branch off the trachea.'),
                                                                                   (gen_random_uuid(), 'I10', 'Essential hypertension', 'High blood pressure that does not have a known secondary cause.'),
                                                                                   (gen_random_uuid(), 'E11.9', 'Type 2 diabetes mellitus', 'Type 2 diabetes without complications.'),
                                                                                   (gen_random_uuid(), 'M54.5', 'Low back pain', 'Pain in the lumbar region of the spine.'),
                                                                                   (gen_random_uuid(), 'J01.90', 'Acute sinusitis, unspecified', 'Acute infection or inflammation of the sinuses.')
ON CONFLICT (icd_10_code) DO NOTHING;