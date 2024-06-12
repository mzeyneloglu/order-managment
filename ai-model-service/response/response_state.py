from pydantic import BaseModel
from typing import Optional

class VoiceInfoResponse(BaseModel):
    ticketNo: str

class RecognizerState:
    def __init__(self):
        self.text = None

class Request(BaseModel):
    customerId: int
    quantity: int

class CustomerClientResponse(BaseModel):
    customerId: Optional[int] = None
    customerName: Optional[str] = None
    customerEmail: Optional[str] = None
    customerPhone: Optional[str] = None
    customerAddress: Optional[str] = None
    customerSurname: Optional[str] = None

class ProductClientResponse(BaseModel):
    productId: Optional[int] = None
    productName: Optional[str] = None
    productPrice: Optional[float] = None
    productDescription: Optional[str] = None
    productCategory: Optional[str] = None
    productTicketNo: Optional[str] = None
    imageUrl: Optional[str] = None

class CourierClientResponse(BaseModel):
    packageStatus: Optional[str] = None

class OrderResponse(BaseModel):
    customerClientResponse: Optional[CustomerClientResponse] = None
    productClientResponse: Optional[ProductClientResponse] = None
    quantity: Optional[int] = None
    dateOfOrder: Optional[str] = None
    courierClientResponse: Optional[CourierClientResponse] = None
    message: Optional[str] = None
