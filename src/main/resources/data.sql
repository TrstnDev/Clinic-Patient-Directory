-- ==========================================
-- 1. Insert Physician Roles
-- ==========================================
INSERT INTO roles (id, role_code, role_name, role_title) VALUES
                                                             (gen_random_uuid(), 'MO', 'Medical Officer', 'Dr.'),
                                                             (gen_random_uuid(), 'SPEC', 'Specialist Physician', 'Dr.'),
                                                             (gen_random_uuid(), 'PROF', 'Head of Department', 'Prof.'),
                                                             (gen_random_uuid(), 'REG', 'Registrar', 'Dr.')
    ON CONFLICT (role_code) DO NOTHING;

-- ==========================================
-- 2. Insert Medical Specialties
-- ==========================================
INSERT INTO specialties (id, specialty_code, specialty_title) VALUES
                                                                  (gen_random_uuid(), 'GP', 'General Practice'),
                                                                  (gen_random_uuid(), 'CARD', 'Cardiology'),
                                                                  (gen_random_uuid(), 'NEUR', 'Neurology'),
                                                                  (gen_random_uuid(), 'ORTH', 'Orthopaedic Surgery'),
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