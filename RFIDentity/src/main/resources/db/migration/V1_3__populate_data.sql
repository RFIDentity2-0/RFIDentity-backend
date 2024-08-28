INSERT INTO  inventory
(id)
VALUES
    (1);

INSERT INTO sap_item
(inventory_id, asset_id, capitalized_date, description, room)
VALUES
    (1, '42296-0', '2023-12-11', 'Access Point Cisco C9124AXI-E         PO4501380209', 'IT'),
    (1, '42063-0', '2023-12-11', 'Kamera Canon XA50  /p. Nagran         PO4501299998', 'IT'),
    (1, '81938-0', '2022-12-29', 'Szafa serwerowa RACK 19" 42U          PO4501287726', 'IT NVM'),
    (1, '81692-0', '2020-10-23', 'Skaner kodow kreskowych COGNEX DM8600V  4501091655', 'IT DC'),
    (1, '81040-0', '2015-06-30', 'Centrala telefoniczna UNIFY           PO4500707527' , 'IT'),
    (1, '42372-0', '2024-02-12', 'Laptop Dell Precision 7680CTO          PO451386413', 'IT');

INSERT INTO vm_item
(inventory_id, asset_id, system_name, status, location, building, room)
VALUES
    (1, '42296-0', 'RZENWAP00049', 'Planned', '001/040_IT_Storage', '1' ,'040_IT_Storage'),
    (1, '42063-0', 'RKA00040',     'Available in Use', '001/GreenRoom', '1', 'GreenRoom'),
    (1, '81692-0', 'RSPARE-PARTS', 'Not in Use - Stock', '001/040_IT_Storage', '1', '040_IT_Storage'),
    (1, '81040-0', 'RSP00172', 'Not in Use - Damaged', 'DAMAGE', '1', 'DAMAGE'),
    (1, '42372-0', 'RPC02172', 'Not in Use - Stock', '001/040_IT_Storage', '1', '040_IT_Storage');

INSERT INTO inventory_assets_outcome
(inventory_id, asset_id, location, status, comment, scanned_date)
VALUES
    (1, '81692-0', '001/040_IT_Storage', 'NEW', 'Wytarty barcode', NOW() ),
    (1, '42296-0', '001/040_IT_Storage', 'NEW', '', NOW() );

