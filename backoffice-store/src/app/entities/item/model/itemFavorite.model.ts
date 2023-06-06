export class ItemFavorite {


	constructor($id: number|undefined, $name: string, $price: number,$favorite: boolean, $categoryId?: number, $categoryName?: string,$description?: string,$image?: string) {
		this.id = $id;
		this.name = $name;
		this.price = $price;
		this.categoryId = $categoryId;
		this.categoryName = $categoryName;
		this.description=$description;
		this.image=$image;
        this.favorite= $favorite;
	}
    id: number|undefined;
    name: string;
    price: number;
	categoryId?: number;
	categoryName?: string;
    description?: string;
    image?: string;
	favorite: boolean;

}