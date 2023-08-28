import { Component, OnInit } from '@angular/core';
import { TopicService } from '../service/topic.service';

@Component({
  selector: 'app-topic-config',
  templateUrl: './topic-config.component.html',
  styleUrls: ['./topic-config.component.scss']
})
export class TopicConfigComponent implements OnInit{

  public topicList: any = [];
  public selectedTopic: string= "";

  constructor(private topicService: TopicService) {}

  ngOnInit(): void {
    this.getAllTopics();
  }

  getAllTopics() {
    this.topicService.getTopics()
    .subscribe(res => {
      this.topicList = res;
    })
  }

  //* SELECT TOPIC
  selectTopic(topicTitle: string) {
    this.topicService.setSelectedTopic(topicTitle);
    this.selectedTopic = topicTitle;
    console.log(this.selectedTopic);
  }
  
}
