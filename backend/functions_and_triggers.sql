create or replace function update_average_alcohol_rating() returns trigger
as
$body$
begin
    UPDATE "dbAlco_alcohol"
    SET average_rating = (SELECT avg(R.rating)
                          FROM "dbAlco_alcoholrating" as R
                          WHERE R.alcohol_id = NEW.alcohol_id)
    WHERE "dbAlco_alcohol".id = NEW.alcohol_id;
    RETURN NEW;
end;
$body$
    LANGUAGE plpgsql VOLATILE;

drop trigger if exists update_average_alcohol_rating_trigger on "dbAlco_alcoholrating";
create trigger update_average_alcohol_rating_trigger
    after insert
    on "dbAlco_alcoholrating"
    for each row
execute procedure update_average_alcohol_rating();


create or replace function update_average_drink_rating() returns trigger
as
$body$
begin
    UPDATE "dbAlco_drink"
    SET average_rating = (SELECT avg(R.rating)
                          FROM "dbAlco_drinkrating" as R
                          WHERE R.drink_id = NEW.drink_id)
    WHERE "dbAlco_drink".id = NEW.drink_id;
    RETURN NEW;
end;
$body$
    LANGUAGE plpgsql VOLATILE;

drop trigger if exists update_average_drink_rating_trigger on "dbAlco_drinkrating";
create trigger update_average_drink_rating_trigger
    after insert
    on "dbAlco_drinkrating"
    for each row
execute procedure update_average_drink_rating();