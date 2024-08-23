CREATE TABLE inventory (
                       id SERIAL PRIMARY KEY,
                       date DATE
);

CREATE TABLE inventory_item (
                                id BIGSERIAL PRIMARY KEY,
                                inventory_id BIGINT NOT NULL,
                                sap_item_id VARCHAR(255),
                                vm_item_id VARCHAR(255),
                                FOREIGN KEY (inventory_id) REFERENCES inventory(id) ON DELETE CASCADE
);

CREATE TABLE inventory_item_outcome (
                                        id BIGSERIAL PRIMARY KEY,
                                        inventory_item_id BIGINT NOT NULL,
                                        location VARCHAR(255),
                                        comment TEXT,
                                        status VARCHAR(255),
                                        description TEXT,
                                        asset_id VARCHAR(255),
                                        scanned_date DATE,
                                        FOREIGN KEY (inventory_item_id) REFERENCES inventory_item(id) ON DELETE CASCADE
);

CREATE TABLE sap_item (
                          id BIGSERIAL PRIMARY KEY,
                          inventory_id BIGINT NOT NULL ,
                          asset_no BIGINT,
                          sub_no BIGINT,
                          capitalized_date DATE,
                          description TEXT,
                          room VARCHAR(255),
                          asset_id VARCHAR(255),
                          FOREIGN KEY (inventory_id) REFERENCES inventory(id) ON DELETE CASCADE
);

CREATE TABLE vm_item (
                         id BIGSERIAL PRIMARY KEY,
                         inventory_id BIGINT NOT NULL,
                         system_name VARCHAR(255),
                         dns_name VARCHAR(255),
                         type VARCHAR(255),
                         manufacturer VARCHAR(255),
                         hardware_type VARCHAR(255),
                         serial_no VARCHAR(255),
                         date_of_installation DATE,
                         status VARCHAR(255),
                         department VARCHAR(255),
                         person_id VARCHAR(255),
                         last_name VARCHAR(255),
                         first_name VARCHAR(255),
                         location VARCHAR(255),
                         building VARCHAR(255),
                         room VARCHAR(255),
                         asset_id VARCHAR(255),
                         FOREIGN KEY (inventory_id) REFERENCES inventory(id) ON DELETE CASCADE
);