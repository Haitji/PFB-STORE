export class CarritoItem{
    id: number;
    name: string;
    price: number;
	categoryName?: string;
    image?: string;
    units: number;

    
	constructor($id: number, $name: string, $price: number, $units: number,$categoryName?: string, $image?: string) {
		this.id = $id;
		this.name = $name;
		this.price = $price;
		this.units = $units;
        this.categoryName = $categoryName;
        this.image = $image;
	}
}