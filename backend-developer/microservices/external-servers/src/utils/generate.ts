import { IResponse } from "../types/response";

function genDateWithTime(): Date {
  const salt = Math.trunc(Math.random() * 10);
  const day = ((salt * 10) % 30) + 1;
  const month = ((salt * 5) % 12) + 1;
  const year = salt + 10;

  const fullDate = `${month}-${day}-20${year}`;

  return new Date(fullDate);
}

export function genDescription(cpf: String): IResponse {
  const salt = Math.trunc(Math.random() * 10);

  const init = `A pessoa inscrita no cpf: ${cpf} se envolveu em um`;
  const events = ["acidente", "assalto", "golpe"];
  const guilt = ["culpado", "vítima", "cumplice", "espectador", "testemunha"];

  const date = genDateWithTime().toISOString();

  const description = `${init} ${events[salt % events.length]} como ${
    guilt[salt % guilt.length]
  } em ${date}`;

  return {
    cpf,
    date,
    description,
  };
}
