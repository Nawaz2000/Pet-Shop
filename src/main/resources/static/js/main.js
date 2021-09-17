const productQuntity = (productid,cardid,event)=>{
    const qty = event.target.value
    window.location.href = `cart.php?productin=${productid}&qty=${qty}&cartid=${cardid}`
}

const handleCheckout = () =>{
    const payment = document.querySelectorAll('.form-check-input');
    payment.forEach((item)=>{
        if(item.checked){
            document.getElementById('payment').innerHTML = `<input type='hidden' name='pay' value='${item.value}'>`;
        }
    })
    
}