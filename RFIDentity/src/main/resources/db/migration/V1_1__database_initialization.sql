CREATE TABLE inventory
(
    id   SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE sap_item
(
    inventory_id     BIGINT      NOT NULL,
    asset_id         VARCHAR(64) NOT NULL,
    capitalized_date DATE,
    description      TEXT,
    room             VARCHAR(255),

    PRIMARY KEY (inventory_id, asset_id),
    FOREIGN KEY (inventory_id) REFERENCES inventory (id) ON DELETE CASCADE
);

CREATE TABLE vm_item
(
    inventory_id         BIGINT      NOT NULL,
    asset_id             VARCHAR(64) NOT NULL,
    system_name          VARCHAR(255),
    dns_name             VARCHAR(255),
    type                 VARCHAR(255),
    manufacturer         VARCHAR(255),
    hardware_type        VARCHAR(255),
    serial_no            VARCHAR(255),
    date_of_installation DATE DEFAULT now(),
    status               VARCHAR(255),
    department           VARCHAR(255),
    person_id            VARCHAR(255),
    last_name            VARCHAR(255),
    first_name           VARCHAR(255),
    location             VARCHAR(255),
    building             VARCHAR(255),
    room                 VARCHAR(255),

    PRIMARY KEY (inventory_id, asset_id),
    FOREIGN KEY (inventory_id) REFERENCES inventory (id) ON DELETE CASCADE
);

CREATE table inventory_assets
(
    id           SERIAL PRIMARY KEY,
    inventory_id BIGINT      NOT NULL,
    sap_asset_id VARCHAR(64) NOT NULL,
    vm_asset_id  VARCHAR(64) NOT NULL,


    FOREIGN KEY (inventory_id, sap_asset_id) REFERENCES sap_item (inventory_id, asset_id) ON DELETE CASCADE,
    FOREIGN KEY (inventory_id, vm_asset_id) REFERENCES vm_item (inventory_id, asset_id) ON DELETE CASCADE,
    FOREIGN KEY (inventory_id) REFERENCES inventory (id) ON DELETE CASCADE
);

CREATE TABLE inventory_assets_outcome
(
    id                BIGSERIAL PRIMARY KEY,
    inventory_id      BIGINT NOT NULL,
    asset_id          VARCHAR(64),
    location          VARCHAR(255),
    status            VARCHAR(255),
    comment           TEXT,
    scanned_date      DATE,

    UNIQUE (inventory_id, asset_id)
);


