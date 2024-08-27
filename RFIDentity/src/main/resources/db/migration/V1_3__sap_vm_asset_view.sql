CREATE VIEW sap_vm_asset_view AS
SELECT
    COALESCE(si.asset_id, vi.asset_id) AS asset_id,
    si.description,
    si.room AS sap_room,
    vi.system_name,
    vi.dns_name,
    vi.type,
    vi.manufacturer,
    vi.hardware_type,
    vi.serial_no,
    vi.status,
    vi.department
FROM
    sap_item si
        FULL OUTER JOIN
    vm_item vi ON si.asset_id = vi.asset_id
WHERE
    COALESCE(si.inventory_id, vi.inventory_id) = (SELECT MAX(id) FROM inventory);

CREATE VIEW location_assets_summary AS
SELECT
    COUNT(vi.asset_id) OVER (PARTITION BY io.location) AS asset_count,
        vi.asset_id,
    vi.location,
    si.description,
    io.status
FROM
    sap_item si
        LEFT JOIN vm_item vi ON si.asset_id = vi.asset_id AND si.inventory_id = vi.inventory_id
        LEFT JOIN inventory_assets_outcome io ON si.asset_id = io.asset_id AND si.inventory_id = io.inventory_id
WHERE
    si.inventory_id = (SELECT MAX(id) FROM inventory)
ORDER BY
    vi.location, io.asset_id
    LIMIT 8;