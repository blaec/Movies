ALTER TYPE resolution_type ADD VALUE 'VGA';
SELECT enum_range(NULL::resolution_type);
SELECT unnest(enum_range(NULL::resolution_type));