import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent implements OnInit {

  gallery = ['https://scontent.fbud2-1.fna.fbcdn.net/v/t1.0-9/13417684_1258029234208993_1848546481528271143_n.jpg?oh=cde3eb9d012f6ae121ae15f7eb7a55e9&oe=5AE1C81D', 'https://scontent.fbud2-1.fna.fbcdn.net/v/t1.0-9/13510881_1266674383344478_7102271159447084254_n.jpg?oh=d1fffc7686e51306f9c2be2b1bb64305&oe=5AF36B78', 'https://scontent.fbud2-1.fna.fbcdn.net/v/t1.0-9/13627148_1277460778932505_536517510599682712_n.jpg?oh=4a90a4227d8f2112328aef0104c2b467&oe=5AE5FE2C', 'https://scontent.fbud2-1.fna.fbcdn.net/v/t1.0-9/13620150_1286774894667760_1352658451728602408_n.jpg?oh=3870aad2c86da844b78d6e51e7151550&oe=5AF2A08A', 'https://scontent.fbud2-1.fna.fbcdn.net/v/t1.0-9/13645298_1286774928001090_8407377595542711259_n.jpg?oh=63864922b279c24420f5b2ac78e13755&oe=5B26B435', 'https://scontent.fbud2-1.fna.fbcdn.net/v/t1.0-9/13700182_1286774964667753_3830375134527939814_n.jpg?oh=8775c86934ace2e3ada75bffef003b2e&oe=5AEE5821', 'https://scontent.fbud2-1.fna.fbcdn.net/v/t1.0-9/13718558_1286774998001083_2363789118203423932_n.jpg?oh=3f74f14b58870b1a25741d0883a7925d&oe=5B2599CB'];

  constructor() {
  }

  ngOnInit() {
  }

}
