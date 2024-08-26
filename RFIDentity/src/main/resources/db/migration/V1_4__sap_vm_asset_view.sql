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

