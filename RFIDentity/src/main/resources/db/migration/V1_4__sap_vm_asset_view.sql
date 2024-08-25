CREATE VIEW sap_vm_asset_view AS
SELECT
    si.asset_id AS sap_asset_id,
    si.description,
    si.room AS sap_room,
    vi.asset_id AS vm_asset_id,
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
        LEFT JOIN
    vm_item vi ON si.inventory_id = vi.inventory_id AND si.asset_id = vi.asset_id
WHERE
    si.inventory_id = (SELECT MAX(id) FROM inventory);

