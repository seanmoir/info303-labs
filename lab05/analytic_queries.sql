select Product_ID, Name, Supplier, Brand, Product_Type,
    round(avg(Unit_Cost) over (partition by Supplier), 2) as Avg_Cost_Supplier,
    round(avg(Unit_Cost) over (partition by Brand), 2) as Avg_Cost_Brand,
    round(avg(Unit_Cost) over (partition by Product_Type), 2) as Avg_Cost_Type
from is303.Product
order by Product_ID;
 
with Ranked as 
    (select Customer_ID, First_Name, Last_Name, sum(Quantity * Price) as total,
        rank() over(order by sum(Quantity * Price) desc) as Customer_Rank
    from is303.Customer inner join IS303.sale_head using (Customer_ID)
            inner join IS303.sale_line using (Sale_ID)
    group by Customer_ID, First_Name, Last_Name)
    select Customer_ID, First_Name, Last_Name, total
    from Ranked
    order by Customer_Rank;
    
    
with Ranked as 
    (select Product_ID, Name, Product_Type, sum(Quantity) as newQuan,
        rank() over (partition by Product_Type
            ORDER BY sum(Quantity) ASC) as Product_Rank
    from is303.Product inner join IS303.sale_line using (Product_ID)
    group by Product_ID, Name, Product_Type)
select Product_ID, Name, Product_Type, newQuan
from Ranked
where Product_Rank = 1
order by Product_Type;