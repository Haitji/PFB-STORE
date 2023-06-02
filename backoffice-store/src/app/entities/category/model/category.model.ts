export class Category{

	constructor($id: number , $name: string, $description?: string, $image?: string) {
		this.id = $id;
		this.name = $name;
		this.description = $description;
		this.image = $image;
	}
    id: number | undefined;
    name: string;
    description?: string;
    image?: string;


}