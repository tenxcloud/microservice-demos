#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:5.0-buster-slim AS base
WORKDIR /app
EXPOSE 80

FROM mcr.microsoft.com/dotnet/sdk:5.0-buster-slim AS build
WORKDIR /src
COPY ["NetCoreDemoA/NetCoreDemoA.csproj", "NetCoreDemoA/"]
RUN dotnet restore "NetCoreDemoA/NetCoreDemoA.csproj"
COPY . .
WORKDIR "/src/NetCoreDemoA"
RUN dotnet build "NetCoreDemoA.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "NetCoreDemoA.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "NetCoreDemoA.dll"]
