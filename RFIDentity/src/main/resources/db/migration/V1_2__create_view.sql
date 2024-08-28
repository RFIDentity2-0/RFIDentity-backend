CREATE VIEW current_inventory_assets AS
SELECT
    i.id AS inventory_id,
    COALESCE(si.asset_id, vi.asset_id) AS asset_id,
    si.description,
    si.room AS sap_room,
    vi.status,
    COALESCE(vi.location, 'Default room') as location,
    vi.building,
    vi.room,
    CASE
        WHEN vi.asset_id IS NULL THEN 'Lack in VM'
        WHEN vi.status = 'Not in Use - Damaged' THEN 'Damaged'
        WHEN vi.status = 'Not in Use - Stock' THEN 'In Stock'
    END AS item_status
FROM inventory i
    JOIN sap_item si ON i.id = si.inventory_id
    LEFT JOIN vm_item vi ON si.inventory_id = vi.inventory_id AND si.asset_id = vi.asset_id
WHERE
    si.inventory_id = (SELECT MAX(id) FROM inventory);

CREATE VIEW current_inventory_assets_with_outcome AS
SELECT
    i.id AS inventory_id,
    cia.asset_id,
    cia.description,
    cia.sap_room,
    cia.status,
    cia.location,
    cia.building,
    cia.room,
    cia.item_status,
    io.location AS inventory_location,
    io.status AS inventory_status,
    io.comment,
    io.scanned_date
FROM inventory i
    JOIN current_inventory_assets cia ON i.id = cia.inventory_id
    LEFT JOIN inventory_assets_outcome io ON io.inventory_id = cia.inventory_id AND io.asset_id = cia.asset_id
WHERE
    cia.inventory_id = (SELECT MAX(id) FROM inventory);

CREATE VIEW current_locations_with_assets_number AS
SELECT
    cia.location,
    (
        SELECT MAX(io.scanned_date) FROM inventory_assets_outcome io WHERE io.location = cia.location
    ) AS scanned_date,
    COUNT(1) as count
FROM current_inventory_assets cia
    LEFT JOIN inventory_assets_outcome io on io.inventory_id = cia.inventory_id AND io.asset_id = cia.asset_id
WHERE
    cia.inventory_id = (SELECT MAX(id) FROM inventory)
GROUP BY cia.location

