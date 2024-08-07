export default interface BaordListItem{
  boardIdx: number;
  boardType : string,
  title: string;
  content : string;
  viewCount : number;
  createDT : string;
  writerNickName : string;
  writerProfileImage : string | null;
}